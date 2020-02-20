import React from 'react';
import {Text, View, StyleSheet, InteractionManager} from 'react-native';
import OptionView from '../common/OptionView';

class QuestionView extends React.Component {
  constructor() {
    super();
    this.state = {
      disabled: false,
    };
  }
  componentDidMount() {
    InteractionManager.runAfterInteractions(() => {
      setTimeout(() => {
        this.props.hideQuestion();
      }, 10000);
    });
  }

  disableView = () => {
    this.setState({disabled: true});
  };
  render() {
    const data = this.props.data;
    return (
      <View style={{flex: 1, padding: 15}}>
        <View style={{paddingBottom: 10}}>
          <OptionView text={data.question} bold disabled />
        </View>
        <View style={{paddingBottom: 10}}>
          <OptionView
            text={data.option1}
            disabled={this.state.disabled}
            disableView={this.disableView}
          />
        </View>
        <View style={{paddingBottom: 10}}>
          <OptionView
            text={data.option2}
            disabled={this.state.disabled}
            disableView={this.disableView}
          />
        </View>
        <View style={{paddingBottom: 10}}>
          <OptionView
            text={data.option3}
            disabled={this.state.disabled}
            disableView={this.disableView}
          />
        </View>
        <View style={{paddingBottom: 10}}>
          <OptionView
            text={data.option4}
            disabled={this.state.disabled}
            disableView={this.disableView}
          />
        </View>
      </View>
    );
  }
}

export default QuestionView;

const styles = StyleSheet.create({});
