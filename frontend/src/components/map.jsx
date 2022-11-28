/*global kakao*/
import React, { useEffect } from "react";
//지도

const Map = (props) => {
  function makeOverListener(map, marker, infowindow) {
    return function () {
      infowindow.open(map, marker);
    };
  }

  // 인포윈도우를 닫는 클로저를 만드는 함수입니다
  function makeOutListener(infowindow) {
    return function () {
      infowindow.close();
    };
  }

  useEffect(() => {
    let options = {
      center: new kakao.maps.LatLng(
        props.selectedSpot.mapy,
        props.selectedSpot.mapx
      ), //mapy=lat,mapx=lng
      level: props.level,
    };
    let container = document.getElementById("map");
    let map = new kakao.maps.Map(container, options);
    var positions = [];
    var selectedpositions = [];

    for (let i = 0; i < props.recommendspot.length; i++) {
      positions.push({
        title: props.recommendspot[i].title,
        latlng: new kakao.maps.LatLng(
          props.recommendspot[i].mapy,
          props.recommendspot[i].mapx
        ),
        content: `<div style="height:150px;width:150px"><img src=${
          props.recommendspot[i].firstImage1
        } style="height:100px;width:150px"><br>${(i + 1).toString()}. ${
          props.recommendspot[i].title
        }</div>`,
      });
    }
    for (let i = 0; i < props.courseSpot.length; i++) {
      selectedpositions.push({
        title: props.courseSpot[i].title,
        latlng: new kakao.maps.LatLng(
          props.courseSpot[i].mapy,
          props.courseSpot[i].mapx
        ),
        content: `<div style="height:150px;width:150px"><img src=${
          props.courseSpot[i].firstImage1
        } style="height:100px;width:150px"><br>${(i + 1).toString()}. ${
          props.courseSpot[i].title
        }</div>`,
      });
    }
    // 마커 이미지의 이미지 주소입니다
    var imageSrc =
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    for (let i = 0; i < positions.length; i++) {
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        clickable: true,
      });
      var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content, // 인포윈도우에 표시할 내용
      });

      kakao.maps.event.addListener(
        marker,
        "mouseover",
        makeOverListener(map, marker, infowindow)
      );
      kakao.maps.event.addListener(
        marker,
        "mouseout",
        makeOutListener(infowindow)
      );

      marker.setMap(map);
    }
    for (var j = 0; j < selectedpositions.length; j++) {
      var selectedmarker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: selectedpositions[j].latlng, // 마커를 표시할 위치
        title: selectedpositions[j].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
        clickable: true,
      });
      var seinfowindow = new kakao.maps.InfoWindow({
        content: selectedpositions[j].content, // 인포윈도우에 표시할 내용
      });

      kakao.maps.event.addListener(
        selectedmarker,
        "mouseover",
        makeOverListener(map, selectedmarker, seinfowindow)
      );
      kakao.maps.event.addListener(
        selectedmarker,
        "mouseout",
        makeOutListener(seinfowindow)
      );

      selectedmarker.setMap(map);
    }
    kakao.maps.event.addListener(map, "zoom_changed", function () {
      props.setLevel(map.getLevel());
    });
    kakao.maps.event.addListener(map, "idle", function () {
      props.setCurrentSpot({
        mapx1: map.getBounds().ha.toString(),
        mapx2: map.getBounds().oa.toString(),
        mapy1: map.getBounds().qa.toString(),
        mapy2: map.getBounds().pa.toString(),
      });
      props.setSelectedSpot({
        title: "",
        mapx: map.getCenter().La,
        mapy: map.getCenter().Ma,
      });
    });
    // 마커를 생성합니다
  }, [props]);

  return (
    <div>
      <div id="map"></div>
    </div>
  );
};

export default Map;
