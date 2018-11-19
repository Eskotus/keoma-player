(ns keoma-player.views.player
  (:require [reagent.core :as r]
            [cljs.core.async :refer [<!] :refer-macros [go]]
            [cljs-http.client :as http]
            [react-bootstrap :as bs]
            [waveform-data :as wf]))

(defn- interpolate-height
  [total-height]
  (fn [size]
    (- total-height (/ (* (+ size 128) total-height) 256))))

(defn draw-canvas-contents [canvas wf-data]
  (let [ctx (.getContext canvas "2d")
        h (.-height canvas)
        w (.-width canvas)
        y (interpolate-height h)
        resampled-wf (.resample wf-data w)
        wf-min (js->clj (.-min resampled-wf))
        wf-max (js->clj (.-max resampled-wf))]
    (println "min count: " (count wf-min))
    (println "offset: " (.-offset_length resampled-wf))
    (.beginPath ctx)
    (dorun
     (map-indexed
      (fn [x val]
        (.lineTo ctx (+ x 0.5) (+ (y val) 0.5)))
      wf-min))
    (dorun
     (map-indexed
      (fn [x val]
        (.lineTo ctx (+ (- (.-offset_length resampled-wf) x) 0.5) (+ (y val) 0.5)))
      (reverse wf-max)))
    (.closePath ctx)
    (set! (.-strokeStyle ctx) "gray")
    (.stroke ctx)
    (set! (.-fillStyle ctx) "gray")
    (.fill ctx)))

(defn div-with-canvas [window-width]
  (let [dom-node (r/atom nil)
        wf-data (r/atom nil)]
    (r/create-class
     {:component-did-update
      (fn [this]
        (if-let [data @wf-data]
         (draw-canvas-contents (.-firstChild @dom-node) data)))

      :component-did-mount
      (fn [this]
        (reset! dom-node (r/dom-node this))
        (go
          (let [response (<! (http/get "/test_data/TOL_6min_720p_download.dat" {:response-type :array-buffer}))]
            (reset! wf-data (.create wf (response :body))))))

      :reagent-render
      (fn [window-width]
        @window-width
        @wf-data
        [:div.with-canvas
                           ;; reagent-render is called before the compoment mounts, so
                           ;; protect against the null dom-node that occurs on the first
                           ;; render
         [:canvas (if-let [node @dom-node]
                    {:width (.-clientWidth node)
                     :height (.-clientHeight node)})]])})))

(defn render
  [window-width]
  [:div.player
    [div-with-canvas window-width]
    [:> bs/ButtonGroup
    [:> bs/Button "Play"]
    [:> bs/Button "Pause"]
    [:> bs/Button "Stop"]]
    [:div.comments
    [:h3 "Comments"]
    [:div.comments-list
      [:> bs/ListGroup
      [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]
      [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]
      [:> bs/ListGroup
        [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]
        [:> bs/ListGroup
        [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]]
        [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]]
      [:> bs/ListGroupItem {:header "Kimmo Kommentoija"} "Kommentin sisältö. Höpö höpö höpöhöpö höpö höpöhöpö höpö."]]]
    [:div.comment-form]]])