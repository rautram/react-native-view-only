import React from 'react';
import {Text, View, StyleSheet, ToolbarAndroid} from 'react-native';
import {connect} from 'react-redux';

class SecondScreen extends React.Component {
  render() {
    return (
      <View style={{flex: 1}}>
        <Text>This is the second View</Text>
        <Text>{this.props.home.home}</Text>
      </View>
    );
  }
}

mapStateToProps = ({home}) => {
  return {home};
};

export default connect(mapStateToProps, null)(SecondScreen);

const styles = StyleSheet.create({});
