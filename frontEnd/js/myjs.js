addEventListener("load", function(){
    setTimeout(hideURLbar, 0); 
}, false);

function hideURLbar(){
     window.scrollTo(0,1); 
}


$("#searchRs").click(function (e) { 
    e.preventDefault();
    let name = $("#inputName").val(); 
    let oldName = $(".weather-details h4").html(); 
    $("#inputName").val(""); 

    let oldPath = window.location.href;
    let newPath = "";
     
    let endSlice = oldPath.length - oldName.length;
    
    if(oldPath.indexOf("getTemp") == -1){
        let finalCharIndex = oldPath.length - 1;
        if(oldPath[finalCharIndex] == "/" ){
            newPath = oldPath + "getTemp/" + name;
        }else{
            newPath = oldPath + "/getTemp/" + name;
        }
    }
    else{
        newPath = oldPath.slice(0, endSlice) + name;
    }
    
    history.pushState("", "", newPath);

    getNewRs(name).then(rs=>{
        let temp = Math.round((rs.temperature.temp - 273.15));
        $(".weather-details h2").html(temp + `<span>o</span>C`);
        $(".weather-details h4").html(rs.name);
        
    }).catch(err => {
        showErrorLog(name);
    })
});


async function getNewRs(name){
    let rs= null;
    await $.ajax({
        type: "GET",
        url:  "http://10.108.126.2:8080/weather/getTemp/"+name,
	   // url: "http://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=347e72f54a7cde54465418abd431fcf0",
        dataType: "json",
        timeout:30000,
        success: function (response) {
            rs = response;
			console.log(rs);
        }
    });
    return rs;
}


function showErrorLog(err){
    alert("Invalid name! " + err + " is not supported in the api! Please try again!");
}

