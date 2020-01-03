function toSubmit(){
	console.log("toSubmit started");
	window.location = "http://localhost:8080/project-1/employee/reimb_submit.html";
}
function toView(){
	console.log("toView started");
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200){
			sessionStorage.setItem("reimb_list", this.responseText);
			let reimbString = sessionStorage.getItem("reimb_list");
			let reimb_list = JSON.parse(reimbString);
			window.location = "http://localhost:8080/project-1/employee/view_reimbursements.html";
		}
		if(this.readyState ===4 && this.status === 204){
			alert("There are no reimbursements");
		}
	}
	
	xhr.open("GET", "http://localhost:8080/project-1/view_reimb");
	xhr.send();
	
}
function submitReimbursement(){
	console.log("submitReimbursement started.");
	let fd = new FormData();
	let submitForm = document.submitForm;
	let amount = submitForm.amount.value;
	let description = submitForm.description.value;
	let reimb_type = submitForm.reimb_type.value;
	fd.append("amount", submitForm.amount.value);
	fd.append("description", submitForm.description.value);
	fd.append("reimb_type", submitForm.reimb_type.value);
	fd.append("receipt", submitForm.receipt.value);
	let receipt = getBase64Image(submitForm.receipt.value);
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			alert("Reimbursement Submitted");
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("There was an error submitting your reimbursement");
		}
		
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/submit_reimb");
	xhr.send(fd);
}

function getBase64Image(img) {
    // Create an empty canvas element
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;

    // Copy the image contents to the canvas
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0);

    // Get the data-URL formatted image
    // Firefox supports PNG and JPEG. You could check img.src to
    // guess the original format, but be aware the using "image/jpg"
    // will re-encode the image.
    var dataURL = canvas.toDataURL("image/png");

    return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
}


function getBase64Image(img){     
	var p;
	var canvas = document.createElement("canvas");
	var img1=document.createElement("img"); 
    img1.setAttribute('src', img); 
    canvas.width = img1.width; 
    canvas.height = img1.height; 
    var ctx = canvas.getContext("2d"); 
    ctx.drawImage(img1, 0, 0); 
    var dataURL = canvas.toDataURL("image/png");
    alert("from getbase64 function"+dataURL );    
    return dataURL;
} 