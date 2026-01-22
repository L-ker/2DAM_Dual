import { View, StyleSheet, Pressable, Text } from 'react-native';
import { useRouter } from 'expo-router';
import { useState } from 'react';

import TeamCard from '@/components/TeamCard';
import teams from '@/constants/Teams';

export default function TeamSelectScreen() {
  const router = useRouter();

  // Índices de los equipos seleccionados
  const [leftIndex, setLeftIndex] = useState(0);
  const [rightIndex, setRightIndex] = useState(1);

  // Cambiar equipo izquierdo
  const changeLeftTeam = () => {
    setLeftIndex((prev) => (prev + 1) % teams.length);
  };

  // Cambiar equipo derecho
  const changeRightTeam = () => {
    setRightIndex((prev) => (prev + 1) % teams.length);
  };

  const startMatch = () => {
    router.push({
      pathname: '/match',
      params: {
        leftTeam: teams[leftIndex].id,
        rightTeam: teams[rightIndex].id,
      },
    });
  };

  return (
    <View style={styles.container}>
      {/* Columnas */}
      <View style={styles.columns}>
        <View style={styles.column}>
          <TeamCard team={teams[leftIndex]} onChange={changeLeftTeam} />
        </View>

        <View style={styles.column}>
          <TeamCard team={teams[rightIndex]} onChange={changeRightTeam} />
        </View>
      </View>

      {/* Botón Play */}
      <Pressable style={styles.playButton} onPress={startMatch}>
        <Text style={styles.playText}>PLAY</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000c3d', // fondo azul oscuro retro
    paddingTop: 40,
  },
  columns: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
  },
  column: {
    width: '45%',
    justifyContent: 'space-between',
  },
  playButton: {
    marginBottom: 30,
    alignSelf: 'center',
    borderWidth: 3,
    borderColor: '#ffffff',
    paddingHorizontal: 40,
    paddingVertical: 12,
  },
  playText: {
    color: '#ffffff',
    fontSize: 18,
    fontWeight: 'bold',
  },
});
