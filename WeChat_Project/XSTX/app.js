// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
  },
  globalData: {
    userInfo: '',
    name: '',
    age: '',
    phone: '',
    address: '',
    
    // 用户唯一标识 openid
    openid: '',

    // 请求的所有活动数据
    tableData: [],

    // 详细数据缓存
    detail_a_id: '',
    detail_a_name: '',
    detail_a_party: '',
    detail_a_lore: '',
    detail_a_formatDate: '',
    detail_a_phone: '',
    detail_a_describe: '',
    detail_a_icon: '',
    detail_a_image: ''
  }
})
