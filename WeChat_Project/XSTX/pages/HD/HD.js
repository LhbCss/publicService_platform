// pages/HD/HD.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tableData: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.setNavigationBarTitle({
      title: '公益活动',
    })
    var that = this;
    // 页面加载时，发送异步请求获取服务器所有活动数据信息
    wx.request({
      url: 'https://publicservice.mynatapp.cc/pages/getAllBrief',
      method: 'GET',
      success: function(resp){
        // 成功获取数据提示框
        wx.showToast({
          title: '成功获取活动页面数据', 
          icon: 'success',
          duration: 1500
      })
      // 将请求数据赋值至 tableData 内
      that.setData({
        tableData: resp.data
      })
    },
      fail: function(resp){
        wx.showToast({
          title: '获取数据失败',
          icon: 'error',
          duration : 1500,
        })}
      })
  },
  /**
   * 跳转至活动详情页面
   */
  toDetail: function(e){
    // 将点击事件所在的行数据写入 globalData 域的数据表格中
    // 通过事件 e.currentTarget.dataset 获取自定义传参数据
    getApp().globalData.detail_a_id = e.currentTarget.dataset.rowdata.a_id;
    getApp().globalData.detail_a_name = e.currentTarget.dataset.rowdata.a_name;
    getApp().globalData.detail_a_lore = e.currentTarget.dataset.rowdata.a_lore;
    getApp().globalData.detail_a_icon = e.currentTarget.dataset.rowdata.a_icon;
    getApp().globalData.detail_a_image = e.currentTarget.dataset.rowdata.a_image;
    getApp().globalData.detail_a_party = e.currentTarget.dataset.rowdata.a_party;
    getApp().globalData.detail_a_phone = e.currentTarget.dataset.rowdata.a_phone;
    getApp().globalData.detail_a_formatDate = e.currentTarget.dataset.rowdata.formatDate;
    getApp().globalData.detail_a_describe = e.currentTarget.dataset.rowdata.a_describe;
    // 跳转至活动详情页面
    wx.navigateTo({
      url: '/pages/detail/detail',
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  })