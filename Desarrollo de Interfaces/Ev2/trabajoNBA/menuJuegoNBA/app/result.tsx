import { View, Text, StyleSheet, Image, Pressable } from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';

import teams from '@/constants/Teams';

type PlayerStats = {
  [player: string]: number;
};

export default function ResultScreen() {
  const router = useRouter();
  const {
    leftTeam,
    rightTeam,
    leftScore,
    rightScore,
    playerStats,
  } = useLocalSearchParams<{
    leftTeam: string;
    rightTeam: string;
    leftScore: string;
    rightScore: string;
    playerStats: string;
  }>();

  const left = teams.find(t => t.id === leftTeam);
  const right = teams.find(t => t.id === rightTeam);

  if (!left || !right) return null;

  const lScore = Number(leftScore);
  const rScore = Number(rightScore);

  // 👇 TIPADO EXPLÍCITO (clave)
  const stats: PlayerStats = JSON.parse(playerStats);

  const topPlayers = Object.entries(stats)
    .sort((a, b) => b[1] - a[1])
    .slice(0, 5);

  const isDraw = lScore === rScore;
  const winner = lScore > rScore ? left : right;

  return (
    <View style={styles.container}>
      {!isDraw ? (
        <>
          <Image source={winner.logo} style={styles.logo} />

          <Text style={styles.winnerName}>{winner.name}</Text>

          <Text style={styles.score}>
            {String(lScore).padStart(2, '0')} - {String(rScore).padStart(2, '0')}
          </Text>

          <Text style={styles.subtitle}>TOP 5 JUGADORES</Text>

          {topPlayers.map(([player, points]) => (
            <Text key={player} style={styles.player}>
              {player} - {points} pts
            </Text>
          ))}
        </>
      ) : (
        <>
          <Text style={styles.draw}>EMPATE</Text>

          <View style={styles.drawLogos}>
            <Image source={left.logo} style={styles.smallLogo} />
            <Image source={right.logo} style={styles.smallLogo} />
          </View>
        </>
      )}

      <Pressable style={styles.button} onPress={() => router.push('/')}>
        <Text style={styles.buttonText}>VOLVER A EMPEZAR</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000c3d',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
  },
  logo: {
    width: 120,
    height: 120,
    resizeMode: 'stretch',
    marginBottom: 12,
  },
  smallLogo: {
    width: 80,
    height: 80,
    resizeMode: 'stretch',
    marginHorizontal: 10,
  },
  winnerName: {
    color: '#ffffff',
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 12,
    textAlign: 'center',
  },
  score: {
    color: '#ffffff',
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 16,
  },
  subtitle: {
    color: '#ffffff',
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  player: {
    color: '#ffffff',
    fontSize: 14,
  },
  draw: {
    color: '#ffffff',
    fontSize: 32,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  drawLogos: {
    flexDirection: 'row',
    marginBottom: 20,
  },
  button: {
    marginTop: 30,
    borderWidth: 2,
    borderColor: '#ffffff',
    paddingHorizontal: 30,
    paddingVertical: 10,
  },
  buttonText: {
    color: '#ffffff',
    fontWeight: 'bold',
  },
});
