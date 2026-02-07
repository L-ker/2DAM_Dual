import { View, Text, StyleSheet, Image, Pressable } from 'react-native';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { useState } from 'react';

import teams from '@/constants/Teams';

type PlayerStats = {
  [playerName: string]: number;
};

export default function MatchScreen() {
  const router = useRouter();
  const { leftTeam, rightTeam } = useLocalSearchParams<{
    leftTeam: string;
    rightTeam: string;
  }>();

  const left = teams.find(t => t.id === leftTeam);
  const right = teams.find(t => t.id === rightTeam);

  const [leftScore, setLeftScore] = useState(0);
  const [rightScore, setRightScore] = useState(0);

  const [playerStats, setPlayerStats] = useState<PlayerStats>({});

  if (!left || !right) return null;

  const addPoints = (
    side: 'left' | 'right',
    player: string,
    points: number
  ) => {
    // sumar al equipo correcto
    if (side === 'left') {
      setLeftScore(prev => prev + points);
    } else {
      setRightScore(prev => prev + points);
    }

    // sumar al jugador
    setPlayerStats(prev => ({
      ...prev,
      [player]: (prev[player] || 0) + points,
    }));
  };

  const finishGame = () => {
    router.push({
      pathname: '/result',
      params: {
        leftTeam: left.id,
        rightTeam: right.id,
        leftScore,
        rightScore,
        playerStats: JSON.stringify(playerStats),
      },
    });
  };

  return (
    <View style={styles.container}>
      {/* MARCADOR */}
      <Text style={styles.score}>
        {String(leftScore).padStart(2, '0')} - {String(rightScore).padStart(2, '0')}
      </Text>

      {/* COLUMNAS */}
      <View style={styles.columns}>
        {/* EQUIPO IZQUIERDO */}
        <View style={styles.card}>
          <Image source={left.logo} style={styles.logo} />
          <Text style={styles.teamName}>{left.name}</Text>

          {left.players.map(player => (
            <View key={player} style={styles.playerRow}>
              <Text style={styles.player}>{player}</Text>

              <Pressable
                style={styles.pointButton}
                onPress={() => addPoints('left', player, 2)}
              >
                <Text style={styles.pointText}>2</Text>
              </Pressable>

              <Pressable
                style={styles.pointButton}
                onPress={() => addPoints('left', player, 3)}
              >
                <Text style={styles.pointText}>3</Text>
              </Pressable>
            </View>
          ))}
        </View>

        {/* VS */}
        <Text style={styles.vs}>VS</Text>

        {/* EQUIPO DERECHO */}
        <View style={styles.card}>
          <Image source={right.logo} style={styles.logo} />
          <Text style={styles.teamName}>{right.name}</Text>

          {right.players.map(player => (
            <View key={player} style={styles.playerRow}>
              <Text style={styles.player}>{player}</Text>

              <Pressable
                style={styles.pointButton}
                onPress={() => addPoints('right', player, 2)}
              >
                <Text style={styles.pointText}>2</Text>
              </Pressable>

              <Pressable
                style={styles.pointButton}
                onPress={() => addPoints('right', player, 3)}
              >
                <Text style={styles.pointText}>3</Text>
              </Pressable>
            </View>
          ))}
        </View>
      </View>

      {/* BOTÓN FIN */}
      <Pressable style={styles.finButton} onPress={finishGame}>
        <Text style={styles.finText}>FIN</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000c3d',
    paddingTop: 40,
  },
  score: {
    color: '#ffffff',
    fontSize: 32,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 16,
  },
  columns: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
  },
  vs: {
    color: '#ffffff',
    fontSize: 24,
    fontWeight: 'bold',
    alignSelf: 'center',
  },
  card: {
    width: '42%',
    borderWidth: 2,
    borderColor: '#ffffff',
    padding: 16,
  },
  logo: {
    width: 80,
    height: 80,
    alignSelf: 'center',
    resizeMode: 'stretch',
    marginBottom: 12,
  },
  teamName: {
    color: '#ffffff',
    fontSize: 16,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 12,
    textTransform: 'uppercase',
  },
  playerRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 6,
  },
  player: {
    color: '#ffffff',
    fontSize: 12,
    flex: 1,
  },
  pointButton: {
    borderWidth: 1,
    borderColor: '#ffffff',
    paddingHorizontal: 6,
    paddingVertical: 2,
    marginLeft: 4,
  },
  pointText: {
    color: '#ffffff',
    fontWeight: 'bold',
  },
  finButton: {
    alignSelf: 'center',
    borderWidth: 3,
    borderColor: '#ffffff',
    paddingHorizontal: 40,
    paddingVertical: 12,
    marginBottom: 20,
  },
  finText: {
    color: '#ffffff',
    fontSize: 18,
    fontWeight: 'bold',
  },
});
