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

      {/* Botón Change */}
      <Pressable style={styles.button} onPress={onChange}>
        <Text style={styles.buttonText}>CHANGE</Text>
      </Pressable>

      {/* Jugadores */}
      <View style={styles.players}>
        {team.players.map((player) => (
          <Text key={player} style={styles.player}>
            {player}
          </Text>
        ))}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginVertical: '40%',
    borderWidth: 2,
    borderColor: '#ffffff',
    padding: 16,
    justifyContent: 'flex-start',
    height: '85%',
  },
  logo: {
    width: 100,
    height: 100,
    marginTop: '45%',
    marginBottom: 40,
    alignSelf: 'center',
    resizeMode: 'stretch',
  },
  teamName: {
    color: '#ffffff',
    fontSize: 20,
    fontWeight: 'bold',
    textAlign: 'center',
    height: '20%',
    marginBottom: 40,
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
    marginBottom: '30%',
  },
  buttonText: {
    color: '#ffffff',
    fontWeight: 'bold',
    textAlign: 'center',
  },
});
