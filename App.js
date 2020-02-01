import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  NativeModules,
  DeviceEventEmitter,
  TouchableOpacity,
  AppState,
  Button,
} from 'react-native';

const FirebaseMessaging = NativeModules.FirebaseMessaging;
const ActivityStarter = NativeModules.ActivityStarter;

class App extends React.Component {
  constructor(props) {
    super(props);
    this.listener = DeviceEventEmitter.addListener('onReceived', event => {
      const data = event;
      alert(JSON.stringify(data));
    });
  }
  componentDidMount() {
    FirebaseMessaging.getData();
    FirebaseMessaging.clearPreference();
  }
  getFirebaseToken = () => {
    FirebaseMessaging.getFirebaseToken().then(token => {
      alert(token);
      console.log(token);
    });
  };
  componentWillUnmount() {
    this.listener.remove();
  }

  goToVideo = () => {
    ActivityStarter.startActivity();
  };
  render() {
    return (
      <View style={{flex: 1}}>
        <Button title="Click Me" onPress={() => this.goToVideo()}></Button>
      </View>
    );
  }
}

export default App;

const styles = StyleSheet.create({});
