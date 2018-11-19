(ns keoma-player.components.waveform
  (:require [reagent.core :as r]
            [cljs.core.async :refer [<!] :refer-macros [go]]
            [cljs-http.client :as http]
            [react-bootstrap :as bs]
            [waveform-data :as wf]))