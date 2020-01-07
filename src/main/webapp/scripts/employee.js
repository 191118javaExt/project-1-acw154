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
	//fd.append("receipt", submitForm.receipt.value);
	//let receipt = getBase64Image(submitForm.receipt.value);
	//var file = submitForm.receipt.files[0];
	console.log(sessionStorage.getItem('receiptArray'));
	fd.append("receipt", sessionStorage.getItem('receiptArray'));
	/*var fileReader = new FileReader();
	if (fileReader && file) {
		fileReader.readAsArrayBuffer(file);
	    fileReader.onload = function () {
	    	var imageData = fileReader.result;
	    	console.log(imageData);
	    };
	    
	}
	done = false;
	while(!done){
		if(fileReader.readyState === 2){
			fd.append("receipt", fileReader.result);
			done = true;
		}
	}*/
	for (var pair of fd.entries()) {
	    console.log(pair[0]+ ', ' + pair[1]); 
	}
	
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			alert("Reimbursement Submitted");
			submitForm.reset();
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("There was an error submitting your reimbursement");
		}
		
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/submit_reimb");
	xhr.send(fd);
}



function getBase64(file) {
	   var reader = new FileReader();
	   reader.readAsDataURL(file);
	   reader.onload = function () {
	     return reader.result;
	   };
	   reader.onerror = function (error) {
	     console.log('Error: ', error);
	   };
}

function getByteArray(file) {
	   var fileReader = new FileReader();
	   if (fileReader && file) {     
	      fileReader.onloadend = function () {
	         var imageData = fileReader.result;
	         console.log(imageData);
	         return imageData;
	      };
	      fileReader.readAsArrayBuffer(file);
	   }
	}
	