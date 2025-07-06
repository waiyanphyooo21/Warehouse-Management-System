<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Vouchers - Ngwe Pone Gyi</title>

  <!-- Bootstrap CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />

  <!-- FontAwesome CDN -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet" />

  <style>
    /* ===== Sidebar ===== */
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .custom-navbar {
      background-color: #2c3e50;
    }
    .custom-navbar .btn {
      color: white;
    }
    .custom-navbar .mx-auto {
      color: white;
      font-weight: 700;
      font-size: 1.5rem;
    }

    .sidebar {
      position: fixed;
      top: 56px; /* Height of navbar */
      left: 0;
      width: 250px;
      height: calc(100vh - 56px);
      background-color: #1e293b;
      color: white;
      overflow-y: auto;
      padding-top: 1rem;
      z-index: 1030;
    }
    .nav-menu {
      display: flex;
      flex-direction: column;
      gap: 0.25rem;
      padding-left: 0.5rem;
    }
    .nav-item {
      padding: 0.35rem 0.75rem;
    }
    .nav-link {
      color: #cbd5e1;
      text-decoration: none;
      font-weight: 500;
      display: flex;
      align-items: center;
      gap: 0.75rem;
      border-radius: 0.375rem;
      transition: background-color 0.3s ease, color 0.3s ease;
    }
    .nav-link:hover {
      background-color: #334155;
      color: #38bdf8;
    }
    .nav-item.active > .nav-link,
    .nav-link.active {
      background-color: #0ea5e9;
      color: white !important;
      font-weight: 700;
    }
    .nav-link i {
      width: 20px;
      font-size: 1.1rem;
    }

    /* Collapse submenu styling */
    .collapse .nav-link {
      padding-left: 2rem;
      font-weight: 400;
      font-size: 0.95rem;
    }
    .collapse .nav-link.active {
      background-color: #0ea5e9;
      font-weight: 600;
    }

    /* Main content */
    .main-content {
      margin-left: 250px;
      padding: 1.5rem;
      background-color: #f8fafc;
      min-height: calc(100vh - 56px);
    }

    /* Offcanvas for mobile */
    @media (max-width: 991.98px) {
      .sidebar {
        display: none;
      }
      .main-content {
        margin-left: 0;
        padding: 1rem;
      }
    }
  </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg custom-navbar fixed-top">
  <div class="container-fluid">
    <button class="btn d-lg-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#mobileSidebar" aria-controls="mobileSidebar">
      <i class="fas fa-bars fa-lg"></i>
    </button>
    <div class="mx-auto">Ngwe Pone Gyi</div>
  </div>
</nav>

<!-- Sidebar Desktop -->
<div class="sidebar d-none d-lg-block">
  <div class="nav-menu">
    <div class="nav-item ${param.active == 'dashboard' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/" class="nav-link ${param.active == 'dashboard' ? 'active' : ''}">
        <i class="fas fa-tachometer-alt"></i> Dashboard
      </a>
    </div>
    <div class="nav-item ${param.active == 'customer' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/customer/list" class="nav-link ${param.active == 'customer' ? 'active' : ''}">
        <i class="fas fa-users"></i> Customer Info
      </a>
    </div>
    <div class="nav-item ${param.active == 'supplier' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link ${param.active == 'supplier' ? 'active' : ''}">
        <i class="fas fa-truck"></i> Supplier Info
      </a>
    </div>
    <div class="nav-item ${param.active == 'warehouse' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link ${param.active == 'warehouse' ? 'active' : ''}">
        <i class="fas fa-warehouse"></i> Warehouse
      </a>
    </div>
    <div class="nav-item ${param.active == 'voucher' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/voucher/list" class="nav-link ${param.active == 'voucher' ? 'active' : ''}">
        <i class="fas fa-ticket-alt"></i> Vouchers
      </a>
    </div>
    <div class="nav-item">
      <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#stockMenu" role="button" aria-expanded="false" aria-controls="stockMenu">
        <span><i class="fas fa-boxes"></i> Stock</span>
        <i class="fas fa-chevron-down small"></i>
      </a>
      <div class="collapse ps-4
            ${param.active == 'stock-in' || param.active == 'stock-out' || param.active == 'stock-balance' ? 'show' : ''}"
           id="stockMenu">
        <a href="${pageContext.request.contextPath}/stock/in-list" class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-in' ? 'active' : ''}">
          Stock In
        </a>
        <a href="${pageContext.request.contextPath}/stock/out-list" class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-out' ? 'active' : ''}">
          Stock Out
        </a>
        <a href="${pageContext.request.contextPath}/stock/balance-list" class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-balance' ? 'active' : ''}">
          Stock Balance
        </a>
      </div>
    </div>
  </div>
</div>

<!-- Offcanvas Sidebar for Mobile -->
<div class="offcanvas offcanvas-start" tabindex="-1" id="mobileSidebar" aria-labelledby="mobileSidebarLabel">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title" id="mobileSidebarLabel">Menu</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <div class="nav-menu">
      <div class="nav-item ${param.active == 'dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/" class="nav-link ${param.active == 'dashboard' ? 'active' : ''}">
          <i class="fas fa-tachometer-alt"></i> Dashboard
        </a>
      </div>
      <div class="nav-item ${param.active == 'customer' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/customer/list" class="nav-link ${param.active == 'customer' ? 'active' : ''}">
          <i class="fas fa-users"></i> Customer Info
        </a>
      </div>
      <div class="nav-item ${param.active == 'supplier' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link ${param.active == 'supplier' ? 'active' : ''}">
          <i class="fas fa-truck"></i> Supplier Info
        </a>
      </div>
      <div class="nav-item ${param.active == 'warehouse' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link ${param.active == 'warehouse' ? 'active' : ''}">
          <i class="fas fa-warehouse"></i> Warehouse
        </a>
      </div>
      <div class="nav-item ${param.active == 'voucher' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/voucher/list" class="nav-link ${param.active == 'voucher' ? 'active' : ''}">
          <i class="fas fa-ticket-alt"></i> Vouchers
        </a>
      </div>
      <div class="nav-item">
        <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#mobileStockMenu" role="button" aria-expanded="false" aria-controls="mobileStockMenu">
          <span><i class="fas fa-boxes"></i> Stock</span>
          <i class="fas fa-chevron-down small"></i>
        </a>
        <div class="collapse ps-4" id="mobileStockMenu">
          <a href="${pageContext.request.contextPath}/stock/in-list" class="nav-link d-block py-2 px-3 rounded">
            Stock In
          </a>
          <a href="${pageContext.request.contextPath}/stock/out-list" class="nav-link d-block py-2 px-3 rounded">
            Stock Out
          </a>
          <a href="${pageContext.request.contextPath}/stock/balance-list" class="nav-link d-block py-2 px-3 rounded">
            Stock Balance
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Main Content -->
<div class="main-content">
  <h2 class="mb-4">Voucher List</h2>
  <!-- Example Table -->
  <table class="table table-striped table-bordered">
    <thead class="table-primary">
    <tr>
      <th>Voucher ID</th>
      <th>Voucher Code</th>
      <th>Description</th>
      <th>Discount (%)</th>
      <th>Valid Until</th>
      <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <!-- Example static data, replace with your dynamic data -->
    <tr>
      <td>1</td>
      <td>SUMMER2025</td>
      <td>Summer discount for all customers</td>
      <td>15</td>
      <td>2025-09-30</td>
      <td><span class="badge bg-success">Active</span></td>
    </tr>
    <tr>
      <td>2</td>
      <td>WELCOME10</td>
      <td>New user welcome offer</td>
      <td>10</td>
      <td>2025-12-31</td>
      <td><span class="badge bg-danger">Expired</span></td>
    </tr>
    <!-- Add more rows dynamically from your backend -->
    </tbody>
  </table>
</div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
