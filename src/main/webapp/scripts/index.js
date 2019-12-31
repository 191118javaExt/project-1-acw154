function sendEmployeeLogin() {
	console.log("sendLogin started.");
	let loginForm = document.loginForm;
	let username = loginForm.username.value;
	let password = loginForm.password.value;
	let loginTemplate = {
			username: username,
			password: password,
	};

	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			sessionStorage.setItem('currentUser', this.responseText);
			let userString = sessionStorage.getItem('currentUser');
			let currentUser = JSON.parse(userString);
			window.location = "http://localhost:8080/project-1/employee/home.html";
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("Failed to login! Username/Password Combination Incorrect OR User is not a Employee.");
		}
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/login");
	xhr.send(JSON.stringify(loginTemplate));
}

function sendFMLogin(){
	console.log("sendLogin started.");
	let loginForm = document.loginForm;
	let username = loginForm.username.value;
	let password = loginForm.password.value;
	let loginTemplate = {
			username: username,
			password: password,
	};

	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			sessionStorage.setItem('currentUser', this.responseText);
			let userString = sessionStorage.getItem('currentUser');
			let currentUser = JSON.parse(userString);
			window.location = "http://localhost:8080/project-1/fm/home.html";
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("Failed to login! Username/Password Combination Incorrect OR User is not a FinanceManager.");
		}
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/fmlogin");
	xhr.send(JSON.stringify(loginTemplate));
}

function registerUser(){
	console.log("registerUser started.");
	let registerForm = document.registerForm;
	let username = registerForm.username.value;
	let password = registerForm.password.value;
	let email = registerForm.email.value;
	let fname = registerForm.fname.value;
	let lname = registerForm.lname.value;
	let role = registerForm.role.value;
	let registerTemplate = {
			username: username,
			password: password,
			email: email,
			fname: fname,
			lname: lname,
			role: role
	};

	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {

		if(this.readyState === 4 && this.status === 200) {
			console.log(this.responseText);
			alert("User Account created")
			//window.location = "http://localhost:8080/project-1/";
		}
		
		if(this.readyState === 4 && this.status === 204) {
			alert("Failed to Register User, Username already exists");
		}
	};
	
	
	xhr.open("POST", "http://localhost:8080/project-1/register");
	xhr.send(JSON.stringify(registerTemplate));
}
