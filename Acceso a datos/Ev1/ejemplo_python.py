#!/usr/bin/env python3
import csv
from pathlib import Path

CSV = Path("data.csv")

def load():
    rows = []
    if not CSV.exists():
        return rows
    with CSV.open(newline='', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        for r in reader:
            rows.append({'id': int(r['id']), 'name': r.get('name',''), 'age': int(r.get('age',0))})
    return rows

def save(rows):
    with CSV.open('w', newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(f, fieldnames=['id','name','age'])
        writer.writeheader()
        for r in rows:
            writer.writerow({'id': r['id'], 'name': r['name'], 'age': r['age']})

def next_id(rows):
    return max([r['id'] for r in rows], default=0) + 1

def list_rows(rows):
    print("ID\tName\tAge")
    for r in rows:
        print(f"{r['id']}\t{r['name']}\t{r['age']}")

def create(rows):
    nid = next_id(rows)
    name = input("Nombre: ").strip()
    age = int(input("Edad: "))
    rows.append({'id': nid, 'name': name, 'age': age})
    print("Creado id=", nid)

def update(rows):
    uid = int(input("ID a actualizar: "))
    r = next((x for x in rows if x['id']==uid), None)
    if not r: print("No encontrado"); return
    print("Actual:", r)
    name = input("Nuevo nombre (ENTER para mantener): ").strip()
    age_s = input("Nueva edad (ENTER para mantener): ").strip()
    if name: r['name'] = name
    if age_s: r['age'] = int(age_s)
    print("Actualizado.")

def delete(rows):
    did = int(input("ID a borrar: "))
    before = len(rows)
    rows[:] = [r for r in rows if r['id'] != did]
    print("Borrado." if len(rows)<before else "No encontrado.")

def find(rows):
    bid = int(input("ID a buscar: "))
    r = next((x for x in rows if x['id']==bid), None)
    print(r if r else "No encontrado")

def main():
    rows = load()
    while True:
        print("\n1)Listar 2)Crear 3)Actualizar 4)Borrar 5)Buscar 6)Guardar y salir 7)Salir sin guardar")
        opt = input("Opción: ").strip()
        if opt == '1': list_rows(rows)
        elif opt == '2': create(rows)
        elif opt == '3': update(rows)
        elif opt == '4': delete(rows)
        elif opt == '5': find(rows)
        elif opt == '6':
            save(rows)
            print("Guardado.")
            break
        elif opt == '7':
            break
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    main()

