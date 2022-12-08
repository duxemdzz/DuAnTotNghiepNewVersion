app.controller("new-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.sortType = 'id';
	$scope.sortReverse = false;
	$scope.searchName = '';

	$scope.initialize = function() {
		//load new
		$http.get("/TechnoShop/rest/news").then(resp => {
			$scope.items = resp.data;
		})
		//load products
		$http.get("/TechnoShop/rest/products").then(resp => {
			$scope.prod = resp.data;
		})
	}
    
    //khoi dau
	$scope.initialize();

    //nut lam moi
	$scope.reset = function() {
		$scope.form = {
		}
	};

    //nut edit
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(0)").tab('show')
	}

    //nut them
    $scope.create = function() {
		var item = angular.copy($scope.form);
		if(item.product==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng chọn sản phẩm!',
				type: 'error',
				timer:'2000'
			})
			}else if(item.title==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập tên tiêu đề!',
				type: 'error',
				timer:'2000'
			})
		   }else if(item.contents==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập nội dung!',
				type: 'error',
				timer:'2000'
			})
		}else{
			$http.post('/TechnoShop/rest/news', item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			if (index == -1) {
				$scope.items.push(resp.data);
				Swal.fire('Thêm thành công', '', 'success')
				$scope.reset();
				$(".nav-pills a:eq(1)").tab('show')
			} else {
				Swal.fire('Danh mục này đã tồn tại!', '', 'error')
			}
		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	 }
	}
	
	//nut cap nhat
	$scope.update = function() {
		var item = angular.copy($scope.form);
		if(item.product==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng chọn sản phẩm!',
				type: 'error',
				timer:'2000'
			})
			}else if(item.title==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập tên tiêu đề!',
				type: 'error',
				timer:'2000'
			})
		   }else if(item.contents==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập nội dung!',
				type: 'error',
				timer:'2000'
			})
		}else{
			$http.put(`/TechnoShop/rest/news/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			Swal.fire('Cập nhật thành công', '', 'success')
			$scope.reset();
			$(".nav-pills a:eq(1)").tab('show')
		}).catch(error => {
			Swal.fire('Cập nhật thất bại', '', 'error')
			console.log("Error", error);
		})
	 }
	}

    //nut xoa
	$scope.delete = function(item) {
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})
		swalWithBootstrapButtons.fire({
			title: 'Bạn có chắc muốn xóa nó không?',
			text: "Xóa tin tức",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Có',
			cancelButtonText: 'Không',
			reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
					$http.delete(`/TechnoShop/rest/news/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					Swal.fire('Xóa thành công', '', 'success')
					$scope.reset();
					$(".nav-pills a:eq(1)").tab('show')
				}).catch(error => {
					Swal.fire('Xóa thất bại', '', 'error')
					console.log("Error", error);
				})
			}
		})
	}

    //phan trang
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	}

})