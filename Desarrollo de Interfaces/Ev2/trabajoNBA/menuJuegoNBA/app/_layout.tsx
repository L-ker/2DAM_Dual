import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import { Stack } from 'expo-router';
import { StatusBar } from 'expo-status-bar';
import 'react-native-reanimated';

import { useColorScheme } from '@/hooks/use-color-scheme';

export const unstable_settings = {
  //anchor: '(tabs)',
};
/**
 * Raiz del proyecto
 * ThemeProvider para controlar tema oscuro o claro 
 * stack navigator para manejar la estructura de pilas
 * tabs= se refiere a las pestañas que estan en (tabs)/_layout.tsx
 * modal= popup que se abre encima de todo
 * statusbar = para notificaciones
 * */ 
export default function RootLayout() {
  const colorScheme = useColorScheme();

  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <Stack>
        {/* Pantalla inicial */}
        <Stack.Screen 
          name="index" 
          options={{ headerShown: false }} 
        />

        {/* Pantalla del partido */}
        <Stack.Screen  
          name="match" 
          options={{ headerShown: false }} 
        />

        {/* Pantalla de resultado */}
        <Stack.Screen 
          name="result" 
          options={{ headerShown: false }} 
        />
      </Stack>
      <StatusBar style="auto" />
    </ThemeProvider>
  );
}
