import React from 'react';
import {Text, StyleSheet} from 'react-native';

const SimpleText = props => {
  return (
    <Text
      style={[
        styles.text,
        {
          fontSize: props.size ? props.size : 16,
          color: props.color ? props.color : 'black',
          fontWeight: props.bold ? 'bold' : 'normal',
        },
      ]}>
      {props.text}
    </Text>
  );
};

export default SimpleText;

const styles = StyleSheet.create({
  text: {
    fontFamily: 'Roboto',
  },
});
