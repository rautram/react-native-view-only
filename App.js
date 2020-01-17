import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  NativeModules,
  DeviceEventEmitter,
  TouchableOpacity,
} from 'react-native';

const FirebaseMessaging = NativeModules.FirebaseMessaging;

class App extends React.Component {
  constructor(props) {
    super(props);
    this.listener = DeviceEventEmitter.addListener('onReceived', event => {
      const data = event;
      console.log('the datas are ', JSON.parse(data.data));
      alert(JSON.stringify(data));
    });
  }
  componentDidMount() {}
  getFirebaseToken = () => {
    FirebaseMessaging.getFirebaseToken().then(token => {
      alert(token);
      console.log(token);
    });
  };
  componentWillUnmount() {
    this.listener.remove();
  }
  render() {
    return (
      <View style={{flex: 1}}>
        <TouchableOpacity onPress={() => this.getFirebaseToken()}>
          <Text>Hello</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

export default App;

const styles = StyleSheet.create({});
