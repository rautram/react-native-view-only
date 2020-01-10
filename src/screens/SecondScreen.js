import React from 'react';
import {
  Text,
  View,
  StyleSheet,
  ToolbarAndroid,
  requireNativeComponent,
} from 'react-native';
import {connect} from 'react-redux';

const SharedElementDetail = requireNativeComponent('SharedElementDetail');

class SecondScreen extends React.Component {
  render() {
    return (
      <View style={{flex: 1}}>
        <SharedElementDetail style={{flex: 1}} />
      </View>
    );
  }
}

mapStateToProps = ({home}) => {
  return {home};
};

export default connect(mapStateToProps, null)(SecondScreen);

const styles = StyleSheet.create({});
