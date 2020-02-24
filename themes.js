
var themes = [];

function addOption(name, button, background, display) {
    var opt = document.createElement('option');
    opt.value = name;
    opt.innerHTML = name;
    $("#themeselector").append(opt);

    themes[name] = [button, background, display];
}

function selection(){
    if ($("#themeselector").val() == "none") {
        document.styleSheets[0].rules[20].style.backgroundImage = "linear-gradient(rgb(101, 105, 118), rgb(75, 80, 94))";
        document.styleSheets[0].rules[12].style.backgroundColor = "rgb(60, 65, 80)";
        document.styleSheets[0].rules[13].style.backgroundColor = "rgb(23, 24, 30)";
        return;
    }

    var selectedColors = themes[$("#themeselector").val()];
    console.log("selected", $("#themeselector").val(), selectedColors);
    document.styleSheets[0].rules[20].style.backgroundImage = selectedColors[0];
    document.styleSheets[0].rules[12].style.backgroundColor = selectedColors[1];
    document.styleSheets[0].rules[13].style.backgroundColor = selectedColors[2];
}

