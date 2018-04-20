<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script src="<c:url value="/js/jquery.1.10.2.min.js" />"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC6v5-2uaq_wusHDktM9ILcqIrlPtnZgEk&sensor=false">
</script>
<script type="text/javascript" >
    function GetLoginDetails() {
        //var uri = 'http://localhost:58479/api/TrackOrder/Get';
        var uri = 'https://trackmyproductservice.azurewebsites.net/api/TrackOrder/Get';
        //var userID = sessionStorage.getItem("userID");
        //Need to change this
        var userID = 1;
        var markers;
        $.ajax({
            type: 'GET',
            url: uri,
            ContentType: "application/x-www-form-urlencoded",
            data: { "key": userID },
            dataType: 'json',
            success: function (data) {

                markers = data;
                var mapOptions = {
                    //center: new google.maps.LatLng(markers[0].Latitude, markers[0].Longitude),
                    center: new google.maps.LatLng(53.328430, -6.304864),
                    zoom: 5,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var infoWindow = new google.maps.InfoWindow();
                var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
                for (i = 0; i < markers.length; i++) {
                    var data = markers[i]
                    //var myLatlng = new google.maps.LatLng(data.Latitude, data.Longitude);
                    var myLatlng = new google.maps.LatLng( 53.328430, -6.304864);
                   
                    var marker = new google.maps.Marker({
                        position: myLatlng,
                        map: map,
                        //title: "Order: "+data.OrderName +" | "+ data.VehicleDetails
                        title: "Order: "+ "AppleiPhone 7" +" | "+ "Vehicle: Volkswagan, Number:IRL 05-D-12365', 1)"
                    });

                }
                (function (marker, data) {
                    google.maps.event.addListener(marker, "click", function (e) {
                        infoWindow.setContent(data.TrackingID);
                        infoWindow.open(map, marker);
                    });

                })(marker, data);;


                
            }

        });
    }
</script>
</head>
<body>

<div id="map_canvas" style="width: 500px; height: 400px"></div>
<input type="button" value="click here"  onclick="GetLoginDetails()"/>
<p>Use this area to provide additional information.</p>
</body>
</html>