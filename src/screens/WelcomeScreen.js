import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  DeviceEventEmitter,
  InteractionManager,
  ToastAndroid,
} from 'react-native';

import FCM from '../nativemodule/FCM';
import QuestionView from '../components/QuestionView';
import questions from '../quiz/questions';
import AnimatedOption from '../common/AnimatedOptionView';

class WelcomeScreen extends React.Component {
  constructor() {
    super();
    this.listener = DeviceEventEmitter.addListener('onReceived', event => {
      const data = event;
      this.sayHello();
    });
    this.state = {
      visible: false,
      count: -1,
      questions: [],
    };
  }
  async componentDidMount() {
    this.setState({
      questions,
    });
    const token = await FCM.getFirebaseToken();
    console.log(token);
  }

  sayHello = () => {
    if (questions.length > this.state.count + 1) {
      this.setState({visible: true, count: this.state.count + 1});
    } else {
      ToastAndroid.show('Questions finishes ', ToastAndroid.SHORT);
    }
  };

  componentWillUnmount() {
    this.listener.remove();
  }

  hideQuestion = () => {
    this.setState({
      visible: false,
    });
  };

  render() {
    return (
      <View style={{flex: 1, padding: 15}}>
        {this.state.visible && (
          <AnimatedOption />
          // <QuestionView
          //   data={this.state.questions[this.state.count]}
          //   hideQuestion={this.hideQuestion}
          // />
        )}
      </View>
    );
  }
}

export default WelcomeScreen;

const styles = StyleSheet.create({});
