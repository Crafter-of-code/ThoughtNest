import React from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import RootPage from '../pages/root/pages/RootPage';

const Stack = createNativeStackNavigator();

function StackNavigation(): React.ReactElement {
  return (
    <Stack.Navigator screenOptions={{ headerShown: false }}>
      <Stack.Screen name="root" component={RootPage} />
    </Stack.Navigator>
  );
}

export default StackNavigation;
