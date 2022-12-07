app.controller("report-ctrl", function($scope, $http) {
	$scope.total = 0;
	$scope.items = [];
	$scope.items2 = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.sortType = 'name'; // set the default sort type
	$scope.sortReverse = false;  // set the default sort order
	$scope.sortType2 = 'name'; // set the default sort type
	$scope.sortReverse2 = false;  // set the default sort order
	$scope.searchName2 = '';     // set the default search/filter term
	$scope.initialize = function() {
		//loadproduct
		$http.get("/rest/report/category").then(resp => {
			$scope.items = resp.data;
		});
	}
	$scope.initialize2 = function() {
		//loadproduct
		$http.get("/rest/report/money").then(resp => {
			$scope.items2 = resp.data;
			$scope.total = $scope.items2
				.map(item => item.group.price * item.sum *(1-(item.group.discount/100)))
				.reduce((total, qty) => total += qty, 0);
		});

	}

	//khoi dau
	$scope.initialize();
	$scope.initialize2();


	$scope.pager = {
		page: 0,
		size: 8,
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
		size: 8,
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