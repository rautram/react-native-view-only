import React from 'react';
import {Text, View, StyleSheet, TouchableOpacity} from 'react-native';
import SimpleText from './SimpleText';

class OptionView extends React.Component {
  constructor() {
    super();
    this.state = {
      color: '#ffffff',
    };
  }

  changeColor = () => {
    this.props.disableView();
    this.setState({
      color: '#43A047',
    });
  };
  render() {
    return (
      <TouchableOpacity
        style={[styles.box, {backgroundColor: this.state.color}]}
        disabled={this.props.disabled ? true : false}
        onPress={() => this.changeColor()}>
        <SimpleText
          text={this.props.text}
          bold={this.props.bold ? true : false}
          color={this.state.color === '#ffffff' ? 'black' : '#ffffff'}
        />
      </TouchableOpacity>
    );
  }
}

export default OptionView;

const styles = StyleSheet.create({
  box: {
    padding: 15,
    borderWidth: 0,
    borderRadius: 40,
    elevation: 3,
    paddingLeft: 25,
  },
});
