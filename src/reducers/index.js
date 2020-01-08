import {combineReducers} from 'redux';
import {homeReducers} from './homeReducers';

const reducers = combineReducers({
  home: homeReducers,
});

export default reducers;
