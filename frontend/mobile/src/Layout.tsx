import { NavigationContainer } from '@react-navigation/native';
import React from 'react';
import StackNavigation from './router/StackNavigation';
function Layout(): React.ReactNode {
  return (
    <>
      <NavigationContainer>
        <StackNavigation />
      </NavigationContainer>
    </>
  );
}
export default Layout;
