import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import SolidButton from '../../../ui/SolidButton';

function RootPage(): React.ReactElement {
  return (
    <SafeAreaView style={styles.mainContainer}>
      <View style={styles.content}>
        <View style={styles.textContainer}>
          <Text style={styles.title}>Welcome to ThoughtNest</Text>

          <Text style={styles.description}>
            Organize your thoughts, ideas and notes in one simple place.
          </Text>
        </View>

        <View style={styles.buttonContainer}>
          <SolidButton heading="Login" />
        </View>
      </View>
    </SafeAreaView>
  );
}

export default RootPage;

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    backgroundColor: '#FFFFFF',
    paddingHorizontal: 24,
  },

  content: {
    flex: 1,
    justifyContent: 'center',
  },

  textContainer: {
    alignItems: 'center',
    marginBottom: 32,
  },

  title: {
    fontSize: 30,
    fontWeight: '700',
    color: '#111111',
    textAlign: 'center',
    marginBottom: 12,
  },

  description: {
    fontSize: 15,
    lineHeight: 23,
    fontWeight: '400',
    color: '#666666',
    textAlign: 'center',
    maxWidth: 320,
  },

  buttonContainer: {
    alignItems: 'center',
  },
});
