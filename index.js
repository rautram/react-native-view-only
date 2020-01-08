/**
 * @format
 */

import {AppRegistry} from 'react-native';
import React from 'react';
import {gestureHandlerRootHOC} from 'react-native-gesture-handler';
import App from './App';
import {name as appName} from './app.json';
import SecondScreen from './src/screens/SecondScreen';

import {Provider} from 'react-redux';

import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';

import reducers from './src/reducers';

const middleware = [thunk, logger];

const store = createStore(reducers, applyMiddleware(...middleware));

function ReduxProvider(Component) {
  return props => (
    <Provider store={store}>
      <Component {...props} />
    </Provider>
  );
}

AppRegistry.registerComponent('example', () =>
  ReduxProvider(gestureHandlerRootHOC(App)),
);

AppRegistry.registerComponent('second', () =>
  ReduxProvider(gestureHandlerRootHOC(SecondScreen)),
);
