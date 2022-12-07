app.controller("order-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.items2 = [];
	$scope.form = {};
	$scope.sortType = 'id';
	$scope.sortReverse = false;
	$scope.searchName = '';
	$scope.initialize = function() {
		$http.get("/rest/orders").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		})
	}
	$scope.editod = function(id) {
		$(".nav-pills a:eq(2)").tab('show')
		$http.get(`/rest/orderdetails/${id}`).then(resp => {
			$scope.items2 = resp.data;
		})
	}
	$scope.initialize();

	$scope.reset = function() {
		$(".nav-pills a:eq(1)").tab('show')
	}


	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(0)").tab('show')
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/orders/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			Swal.fire('Cập nhật thành công', '', 'success')
			$scope.reset();
		}).catch(error => {
			Swal.fire('Cập nhật thất bại!', '', 'error')
			console.log("Error", error);
		})
	}

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
	$scope.pager2 = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items2.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items2.length / this.size);
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