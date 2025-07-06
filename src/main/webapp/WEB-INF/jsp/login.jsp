<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    html, body {
      height: 100%;
      background-color: #f8f9fa;
    }
    .login-wrapper {
      max-width: 850px;
      background: white;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      overflow: hidden;
    }
    .login-image {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    .image-container {
      height: 100%;
    }
    .login-form {
      padding: 25px;
    }
    .btn-orange {
      background-color: #FF4D00;
      border-color: #FF4D00;
      color: white;
      margin-top: 30px;
    }
    .btn-orange:hover {
      background-color: #dd4401;
      border-color: #dd4401;
    }

    /* Consistent height for desktop layout */
    @media (min-width: 768px) {
      .login-row {
        height: 420px;
      }
    }

    /* Mobile: Show full image without cropping and add spacing */
    @media (max-width: 767.98px) {
      .image-container {
        height: 100px;
        padding: 15px 0;
      }
      .login-image {
        height: 100px;
        object-fit: contain;
        max-width: 100%;
        display: block;
        margin: 0 auto;
      }
    }
  </style>
</head>
<body>
<div class="container h-100 d-flex align-items-center justify-content-center">
  <div class="row login-wrapper login-row flex-md-row flex-column w-100">
    <!-- Left Side: Image -->
    <div class="col-md-6 p-0 image-container">
      <img src="${pageContext.request.contextPath}/resources/images/npg.png" alt="Login Image" class="login-image h-100">
    </div>

    <!-- Right Side: Login Form -->
    <div class="col-md-6 login-form d-flex flex-column justify-content-center">
      <h2 class="text-center mb-3">Login</h2>

      <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">Username:</label>
          <input type="text" id="username" name="username" class="form-control" required>
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">Password:</label>
          <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-orange w-100">Login</button>

        <c:if test="${not empty error}">
          <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
      </form>
    </div>
  </div>
</div>
</body>
</html>
