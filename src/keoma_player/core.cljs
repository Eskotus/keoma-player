(ns keoma-player.core
    (:require [keoma-player.views.player :as p]
              [reagent.core :as r]
              [react-bootstrap :as bs]))

(defonce greeting "hello")
(def who "Eero")

(defn app []
  [:div.app.container
   [p/render]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
