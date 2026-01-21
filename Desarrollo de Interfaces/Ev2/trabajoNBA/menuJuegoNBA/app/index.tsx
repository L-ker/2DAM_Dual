import { View, Text, Button, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function TeamSelectScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text>Pantalla de selección de equipos</Text>
      <Button 
        title="Ir al partido" 
        onPress={() => router.push('/match')} 
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});
