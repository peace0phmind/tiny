// 导入表格模版数据
export const productInventoryColumns = [
  {
    title: 'partNumber',
    key: 'partNumber'
  },
  {
    title: 'serialNumber',
    key: 'serialNumber'
  },
  {
    title: 'produceDate',
    key: 'produceDate'
  },
  {
    title: 'batchNumber',
    key: 'batchNumber'
  },
  {
    title: 'status',
    key: 'status'
  }
]

export const productInventoryData = [
  {
    partNumber: '件号',
    serialNumber: '序列号',
    produceDate: '生产日期',
    batchNumber: '批次',
    status: '在库:0,出库:1'
  },
  {
    partNumber: '000001',
    serialNumber: '000001',
    produceDate: '1999-01-01',
    batchNumber: '00001',
    status: '1'
  }
]

// 导入表格模版数据
export const afterSaleInventoryColumns = [
  {
    title: 'customerName',
    key: 'customerName'
  },
  // {
  //   title: 'saleOrder',
  //   key: 'saleOrder'
  // },
  {
    title: 'partNumber',
    key: 'partNumber'
  },
  {
    title: 'version',
    key: 'version'
  },
  {
    title: 'deliveryDate',
    key: 'deliveryDate'
  },
  {
    title: 'qaDate',
    key: 'qaDate'
  },
  {
    title: 'sparePart',
    key: 'sparePart'
  },
  {
    title: 'usage',
    key: 'usage'
  },
  {
    title: 'remark',
    key: 'remark'
  },
  {
    title: 'status',
    key: 'status'
  }
]

export const afterSaleInventoryData = [
  {
    customerName: '客户',
    // saleOrder: '销售订单',
    partNumber: '产品',
    version: '版本号',
    deliveryDate: '交付日期',
    qaDate: '质保日期',
    sparePart: '是否备件',
    usage: '用途',
    remark: '说明',
    status: '状态'
  },
  {
    customerName: 'cuscustomerNametomer',
    // saleOrder: 'saleOrder',
    partNumber: 'partNumber',
    version: 'version',
    deliveryDate: 'deliveryDate',
    qaDate: 'qaDate',
    sparePart: 'sparePart',
    usage: 'usage',
    remark: 'remark',
    status: 'status'
  }
]
