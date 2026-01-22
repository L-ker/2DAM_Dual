import { View, Text, Image, Pressable, StyleSheet } from 'react-native';
import { Team } from '@/constants/Teams';

interface TeamCardProps {
  team: Team;
  onChange: () => void;
}

export default function TeamCard({ team, onChange }: TeamCardProps) {
  return (
    <View style={styles.card}>
      {/* Logo */}
      <Image source={team.logo} style={styles.logo} resizeMode="contain" />

      {/* Nombre del equipo */}
      <Text style={styles.teamName}>{team.name}</Text>

      {/* Jugadores */}
      <View style={styles.players}>
        {team.players.map((player) => (
          <Text key={player} style={styles.player}>
            {player}
          </Text>
        ))}
      </View>

      {/* Botón Change */}
      <Pressable style={styles.button} onPress={onChange}>
        <Text style={styles.buttonText}>CHANGE</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    flex: 1,
    borderWidth: 2,
    borderColor: '#ffffff',
    padding: 16,
    justifyContent: 'space-between',
  },
  logo: {
    width: '100%',
    height: 80,
    marginBottom: 8,
  },
  teamName: {
    color: '#ffffff',
    fontSize: 14,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 8,
    textTransform: 'uppercase',
  },
  players: {
    marginBottom: 12,
  },
  player: {
    color: '#ffffff',
    fontSize: 12,
    textAlign: 'center',
  },
  button: {
    borderWidth: 2,
    borderColor: '#ffffff',
    paddingVertical: 6,
  },
  buttonText: {
    color: '#ffffff',
    fontWeight: 'bold',
    textAlign: 'center',
  },
});
