// @flow

import React, { Component } from 'react';
import './App.css';

import Peaks from '../node_modules/peaks.js/peaks.js';
class App extends Component {
  constructor(props) {
    super(props);
  }
  componentDidMount() {
    const p = Peaks.init({
      container: this._peaksDiv,
      mediaElement: this._audio,
      dataUri: {
        arrayBuffer: '/assets/sample.dat',
        json: 'assets/sample.json'
      },
      logger: console.error.bind(console)
    });
  }
  render() {
    return (
      <div className="App" ref={(elem) => {this._peaksDiv = elem;}}>
          <audio ref={(elem) => {this._audio = elem;}}>
            <source src="assets/sample.mp3" type="audio/mpeg" />
            <source src="assets/sample.ogg" type="audio/ogg" />
            Your browser does not support the audio tag.
          </audio>
      </div>
    );
  }
}

export default App;
