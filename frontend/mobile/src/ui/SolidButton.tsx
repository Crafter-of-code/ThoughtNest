import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';
type props = {
  heading?: string;
  children?: React.ReactNode;
};
const SolidButton = (props: props) => {
  return (
    <TouchableOpacity style={styles.button} activeOpacity={0.6}>
      <Text style={styles.buttonText}>{props.heading}</Text>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    alignItems: 'center',
    justifyContent: 'center',

    paddingVertical: 10,
    paddingHorizontal: 22,

    borderRadius: 10,

    backgroundColor: '#F59E0B',
    borderWidth: 1.5,
    borderColor: '#F59E0B',
    shadowOpacity: 0.15,
    shadowRadius: 2,
    shadowOffset: {
      width: 0,
      height: 1,
    },

    elevation: 3,
  },

  buttonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '500',
    letterSpacing: 0.1,
  },
});

export default SolidButton;
