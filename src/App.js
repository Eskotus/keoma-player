// @flow

import React, { Component } from 'react';
import './App.css';

class App extends Component {
  _peaksDiv: any;
  _audio: any;
  render() {
    return (
      <div className="App" ref={(elem) => {this._peaksDiv = elem}}>
        <audio ref={(elem) => {this._audio = elem}}>
          <source src="assets/sample.mp3" type="audio/mpeg" />
          <source src="assets/sample.ogg" type="audio/ogg" />
          Your browser does not support the audio tag.
        </audio>
      </div>
    );
  }
}

export default App;
