import React from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image } from 'react-native';

export default function Index() {
  return (
    <View style={styles.container}>
      <StatusBar style="light" />
      <View style={styles.card}>
        <Image 
          source={{ uri: 'https://randomuser.me/api/portraits/men/32.jpg' }} 
          style={styles.avatar} 
        />
        
        <View style={styles.codeBlock}>
          <Text style={styles.baseText}>  nombre: <Text style={styles.string}>"Alex Dev"</Text>,</Text>
          <Text style={styles.baseText}>  puesto: <Text style={styles.string}>"Full Stack"</Text>,</Text>
          <Text style={styles.baseText}>  empresa: <Text style={styles.string}>"Tech Corp"</Text>,</Text>
          <Text style={styles.baseText}>  nivel: <Text style={styles.number}>9999</Text></Text>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0d1117',
    alignItems: 'center',
    justifyContent: 'center',
  },
  card: {
    backgroundColor: '#161b22',
    paddingVertical: 40,
    paddingHorizontal: 25,
    borderRadius: 20,
    borderWidth: 1,
    borderColor: '#30363d',
    width: 300,
    alignItems: 'center',
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 10 },
    shadowOpacity: 0.5,
    shadowRadius: 20,
    elevation: 10,
  },
  avatar: {
    width: 100,
    height: 100,
    borderRadius: 50,
    marginBottom: 25,
    borderWidth: 3,
    borderColor: '#58a6ff',
  },
  codeBlock: {
    alignSelf: 'flex-start',
    marginLeft: 15,
    marginBottom: 5,
    marginTop: 5,
  },
  baseText: {
    color: '#c9d1d9',
    fontSize: 16,
    fontFamily: 'monospace',
    marginBottom: 2,
    alignSelf: 'flex-start',
    lineHeight: 24,
  },
  string: {
    color: '#a5d6ff',
  },
  number: {
    color: '#d2a8ff',
  }
});
