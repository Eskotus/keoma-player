(ns keoma-player.core
    (:require [keoma-player.views.player :as p]
              [reagent.core :as r]
              [react-bootstrap :as bs]))

(def window-width (r/atom nil))

(defn on-window-resize [evt]
  (reset! window-width (.-innerWidth js/window)))

(defn app []
  [:div.app.container
   [p/render window-width]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (r/render [app]
            (.getElementById js/document "app"))
  (.addEventListener js/window "resize" on-window-resize))

(defn ^:export init []
  (start))
