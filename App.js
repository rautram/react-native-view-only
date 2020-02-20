import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  NativeModules,
  DeviceEventEmitter,
  requireNativeComponent,
  Button,
} from 'react-native';

const FirebaseMessaging = NativeModules.FirebaseMessaging;
const ActivityStarter = NativeModules.ActivityStarter;

const VideoView = requireNativeComponent('VideoView');

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
        <View
          style={{
            height: 300,
            width: 300,
            borderRadius: 150,
            backgroundColor: 'rgba(0,0,0,0.3)',
          }}>
          <VideoView
            style={{height: 300, width: 300, zIndex: -1, overflow: 'hidden'}}
          />
        </View>
        <Button title="Go to Video" onPress={() => this.goToVideo()} />
      </View>
    );
  }
}

export default App;

const styles = StyleSheet.create({});
