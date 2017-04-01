// @flow

import React from 'react';
import ReactDOM from 'react-dom';
import Peaks from 'peaks.js';
import App from './App';
import './index.css';

const p = Peaks.init({
  container: App._peaksDiv,
  mediaElement: App._audio,
  dataUri: {
    arrayBuffer: 'assets/sample.dat'
  },
  logger: console.error.bind(console),
  
});

ReactDOM.render(
  <App />,
  document.getElementById('root')
);
