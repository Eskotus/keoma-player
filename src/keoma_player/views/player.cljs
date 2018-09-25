(ns keoma-player.views.player
  (:require [reagent.core :as r]
            [react-bootstrap :as bs]))

(defn render
  []
  [:div.player 
   [:div "Audio waveform"]
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