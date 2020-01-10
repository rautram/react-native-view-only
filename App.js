import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  NativeModules,
  TouchableOpacity,
  ToolbarAndroid,
  requireNativeComponent,
} from 'react-native';
import {RectButton} from 'react-native-gesture-handler';
import {connect} from 'react-redux';

const ActivityStarter = NativeModules.ActivityStarter;

const SharedElement = requireNativeComponent('SharedElement');

class App extends React.Component {
  start = () => {
    ActivityStarter.startShareElement();
  };
  render() {
    return (
      <View style={{flex: 1}}>
        <RectButton style={{flex: 1}} onPress={() => this.start()}>
          <Text>This is fragment not a custom View</Text>
          <Text>{this.props.home.home}</Text>
        </RectButton>
        <SharedElement style={{flex: 1}}></SharedElement>
        <View style={styles.button}>
          <Text>Hello</Text>
        </View>
      </View>
    );
  }
}

mapStateToProps = ({home}) => {
  return {home};
};

export default connect(mapStateToProps, null)(App);

const styles = StyleSheet.create({
  button: {
    height: 60,
    margin: 15,
    backgroundColor: 'blue',
    elevation: 3,
    alignItems: 'center',
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    fontWeight: 'bold',
    color: 'white',
  },
  toolbar: {
    backgroundColor: '#2196F3',
    height: 56,
    alignSelf: 'stretch',
    textAlign: 'center',
  },
});
