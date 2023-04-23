function test_left(id) {
    console.log(`testing left ${id}`);
    let id_num = parseInt(id);
    fetch('http://localhost:8080/questions/last')
    .then(response => response.json())
    .then(data => {
        if (data === id_num) {
            // console.log("Why am I not working ami amor ami amor ami amor :(");
            document.getElementById("left-anchor").setAttribute("href", "#");
            document.getElementById("left-button").disabled = true;
        }
    })
    .catch(error => console.log(error));
}

function test_right(id) {
    console.log(`testing right ${id}`);
    let id_num = parseInt(id);
    fetch('http://localhost:8080/questions/first')
    .then(response => response.json())
    .then(data => {
        if (data === id_num) {
            document.getElementById("right-anchor").setAttribute("href", "#");
            document.getElementById("right-button").disabled = true;
        }
    })
    .catch(error => console.log(error));
}