<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
	integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
	crossorigin="anonymous"></script>
<style>
.verticle-button-group .btn {
	margin-bottom: 10px;
	box-sizing: border-box;
}
</style>
<meta charset="UTF-8">
<title>BookYourShow</title>
</head>
<body>
	<h2>Theatre Registration</h2>

	<%-- 	<form:form action="/register-theatre" method="post" modelAttribute="theatre">
	<div>Name: <form:input path="name" /> 
	<p style = color:red> ${theatreExistsError} </p>
	</div>
	<div>Password: <form:input path="password"/> </div>
	
	<form:button> submit</form:button>
	<a href="/login">Login</a> instead
	</form:form> --%>

	<section class="vh-100" style="background-color: #eee;">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-12 col-xl-11">
					<div class="card text-black" style="border-radius: 25px;">
						<div class="card-body p-md-5">
							<div class="row justify-content-center">
								<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

									<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Edit Theatre</p>

									<form:form class="mx-1 mx-md-4" action="/editTheatre"
										method="POST" modelAttribute="theatre">

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-user fa-lg me-3 fa-fw"></i>
											<c:set var="error" scope="session"
												value="${theatreExistsError}" />
											<c:if test="${error != null}">
												<div class=" form-outline flex-fill mb-0 alert alert-danger " role="alert">${theatreExistsError}</div>
											</c:if></div>


											<div class="d-flex flex-row align-items-center mb-4">
												<i class="fas fa-user fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<form:input path="name" type="text" id="form3Example1c"
														class="form-control" />
													<label class="form-label" for="form3Example1c">New Theatre
														Name</label>
												</div>
											</div>


											<div class="d-flex flex-row align-items-center mb-4">
												<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<form:input path="password" type="password"
														id="form3Example4c" class="form-control" />
													<label class="form-label" for="form3Example4c">New Password</label>
												</div>
											</div>


											<div
												class=" justify-content-center mx-4 mb-3 verticle-button-group">
												<form:button class="btn btn-primary btn-lg mt-3">Confirm</form:button>

												<a href="/index" class="btn btn-primary btn-lg mt-3">Cancel</a>
											</div>
									</form:form>

								</div>
								<div
									class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
										class="img-fluid" alt="Sample image">

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>