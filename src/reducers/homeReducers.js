const inititialState = {
  home: 'Tathali-5, Bhaktapur',
};

export function homeReducers(state = inititialState, action) {
  switch (action.type) {
    case 'ADD':
      return {
        ...state,
        home: 'Chabahil',
      };
    default:
      return state;
  }
}
