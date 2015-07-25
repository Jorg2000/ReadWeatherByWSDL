$(document).ready(function() {
    $("#city").hide();
    $("#lCity").hide();
    $("#lcityError").hide();
    $("#cityError").hide();
    $("#weather").hide();
    $("#currentWeather").hide();

});

//Call for country prediction servlet on the backend
$("#country").autocomplete({
    source: function(request, response) {
        $.ajax({
            url: "/countrypredictor",
            dataType: "json",
            type: "POST",
            data: {
                country: request.term
            },
            success: function(data) {
                response($.map(data, function(item) {
                    return item;
                }));
            }
        });
    },
    minLength: 1
});


//Calling for servlet that sends list of the available cities for chosen country
$("#country").keydown(function(eventObject) {
    if (eventObject.which == 13) {
        $("#lCity").hide();
        $("#city").hide();
        $("#lcityError").hide();
        $("#cityError").hide();
        $("#currentWeather").hide();
        $("#weather").hide();
        $("#map-canvas").hide();
        var country = $(this).val();
        var position = $("#country").position();
        startLoadingAnimation(position.top + $("#country").height() * 2, position.left);
        $.ajax({
            type: "POST",
            url: "/cities",
            dataType: 'json',
            data: {
                country: country
            },
            success: function(data) {
                var sel = $("#city");
                sel.empty();
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        sel.append('<option value="' + data[i] + '">' + data[i] + '</option>');
                    }
                    $("#lCity").show();
                    $("#city").show();
                    stopLoadingAnimation();
                } else {
                    $("#lcityError").show();
                    $("#cityError").show();
                    stopLoadingAnimation();
                }
            }
        });
    }
});

//Calling for servlet that returns weather for chosen country&city
$("#city").change(function() {
    var country = $("#country").val();
    var city = $(this).val();
    var position = $("#city").position();
    startLoadingAnimation(position.top + $("#city").height() * 2, position.left);
    $("#currentWeather").hide();
    $("#weather").hide();
    $("#map-canvas").hide();
    $.ajax({
        type: "POST",
        url: "/getweather",
        dataType: 'json',
        data: {
            country: country,
            city: city
        },
        success: function(data) {
            if (data.status == "OK") {
                stopLoadingAnimation();
                var resWeather = "";
                var resWeather = resWeather.concat(
                    "Place: ", data.location.place,
                    "\nLatitude: ", data.location.latitude,
                    "\nLongitude: ", data.location.longitude,
                    "\nTime: ", data.time,
                    "\nWind: ", data.wind,
                    "\nVisibility: ", data.wind,
                    "\nTemperature: ", data.temperature,
                    "\nDew Point: ", data.dewPoint,
                    "\nRelative Humidity", data.relativeHumidity,
                    "\nPressure: ", data.pressure
                );

                $("#weather").val(resWeather);
                $("#currentWeather").show();
                $("#currentWeather").width(400);
                $("#weather").show();
                $("#map-canvas").show();
                loadMap(data.location.latitude, data.location.longitude, data.location.place);

            } else {
                $("#currentWeather").show();
                $("#weather").val("Unfortunately I don't know weather for this airport. Try another :)");
                $("#weather").show()
            }
        }
    });
});

//Showing animation for user during the waiting
function startLoadingAnimation(positionY, positionX) {
    var imgObj = $("#loadImg");
    imgObj.show();
    imgObj.offset({
        top: positionY,
        left: positionY
    });
}

function stopLoadingAnimation() {
    var imgObj = $("#loadImg");
    imgObj.hide();
}

//Loading google maps
function loadMap(lat, lon, name) {
    var myLatlng = new google.maps.LatLng(lat, lon);
    var mapCanvas = document.getElementById('map-canvas');
    var mapOptions = {
        center: myLatlng,
        zoom: 13,
        scrollwheel: false,
        mapTypeId: google.maps.MapTypeId.HYBRID
    }
    var map = new google.maps.Map(mapCanvas, mapOptions)
    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title: name
    });
}