// @flow

import React, { Component } from 'react';
import './App.css';

import Peaks from '../node_modules/peaks.js/peaks.js';

type Props = { prop: string };

class App extends Component {
  _peaksDiv: HTMLDivElement;
  _audio: HTMLAudioElement;
  _zoomIn: HTMLButtonElement;
  _zoomOut: HTMLButtonElement;
  _addSegment: HTMLButtonElement;
  _addPoint: HTMLButtonElement;
  _logData: HTMLButtonElement;

  constructor(props: Props) {
    super(props);
  }
  componentDidMount() {
    var p = Peaks.init({
      container: this._peaksDiv,
      mediaElement: this._audio,
      dataUri: {
        arrayBuffer: '/assets/sample.dat',
        json: 'assets/sample.json'
      },
      logger: console.error.bind(console)
    });
    this._zoomIn.onclick = p.zoom.zoomIn.bind(p);
    this._zoomOut.onclick = function () {
      p.zoom.zoomOut();
    }

    this._addSegment.onclick = function () {
      p.segments.add({
        startTime: p.time.getCurrentTime(),
        endTime: p.time.getCurrentTime() + 10,
        editable: true
      });
    };

    this._addPoint.onclick = function() {
      p.points.add({
        timestamp: p.time.getCurrentTime(),
        editable: true
      });
    };

    this._logData.onclick = function() {
      console.log('Segments', p.segments.getSegments());
      console.log('Points', p.points.getPoints());
    };

  }
  render() {
    return (
      <div className="App">
        <div id="first-waveform-visualiser-container" ref={(elem) => {this._peaksDiv = elem;}} />
        <div id="demo-controls">
          <audio controls="controls" ref={(elem) => {this._audio = elem;}}>
            <source src="assets/sample.mp3" type="audio/mpeg" />
            <source src="assets/sample.ogg" type="audio/ogg" />
            Your browser does not support the audio tag.
          </audio>
          <button ref={(elem) => {this._zoomIn = elem;}}>Zoom in</button>
          <button ref={(elem) => {this._zoomOut = elem;}}>Zoom out</button>
          <button ref={(elem) => {this._addSegment = elem;}} data-action="add-segment">Add a Segment at current time</button>
          <button ref={(elem) => {this._addPoint = elem;}} data-action="add-point">Add a Point at current time</button>
          <button ref={(elem) => {this._logData = elem;}} data-action="log-data">Log segments/points</button>
        </div>
      </div>
    );
  }
}

export default App;
