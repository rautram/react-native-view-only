import React from 'react';
import {Text, View, StyleSheet, Dimensions} from 'react-native';
import Animated, {Easing} from 'react-native-reanimated';
import {runTiming} from './config/runTiming';
import SimpleText from './SimpleText';

const {width} = Dimensions.get('window');

const {Value, Clock} = Animated;

class AnimatedOption extends React.Component {
  constructor(props) {
    super(props);
    this.clock = new Clock();
    this.width = new Value(0);
    this.animWidth = runTiming(this.clock, 50, width - 30);
  }
  render() {
    return (
      <View style={styles.box}>
        <Animated.View
          style={{
            height: 50,
            width: this.animWidth,
            backgroundColor: 'rgba(0,0,0,0.1)',
            borderRadius: 40,
          }}
        />
      </View>
    );
  }
}

export default AnimatedOption;

const styles = StyleSheet.create({
  box: {
    height: 50,
    borderWidth: 0,
    borderRadius: 40,
    elevation: 3,
    backgroundColor: 'white',
  },
});
