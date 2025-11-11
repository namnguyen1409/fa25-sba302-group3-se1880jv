# InvoiceManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create6**](#create6) | **POST** /api/invoices | |
|[**delete6**](#delete6) | **DELETE** /api/invoices/{invoiceId} | |
|[**filter7**](#filter7) | **POST** /api/invoices/filter | |
|[**getById3**](#getbyid3) | **GET** /api/invoices/{invoiceId} | |
|[**getOrdersForStaffToday**](#getordersforstafftoday) | **GET** /api/invoices/staff/today | |
|[**update6**](#update6) | **PUT** /api/invoices/{invoiceId} | |

# **create6**
> CustomApiResponseInvoiceResponse create6(invoiceRequest)


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration,
    InvoiceRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

let invoiceRequest: InvoiceRequest; //

const { status, data } = await apiInstance.create6(
    invoiceRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **invoiceRequest** | **InvoiceRequest**|  | |


### Return type

**CustomApiResponseInvoiceResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete6**
> CustomApiResponseVoid delete6()


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

let invoiceId: string; // (default to undefined)

const { status, data } = await apiInstance.delete6(
    invoiceId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **invoiceId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **filter7**
> CustomApiResponsePageInvoiceResponse filter7(searchFilter)


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter7(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageInvoiceResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getById3**
> CustomApiResponseInvoiceResponse getById3()


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

let invoiceId: string; // (default to undefined)

const { status, data } = await apiInstance.getById3(
    invoiceId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **invoiceId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseInvoiceResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getOrdersForStaffToday**
> CustomApiResponseListInvoiceResponse getOrdersForStaffToday()


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

const { status, data } = await apiInstance.getOrdersForStaffToday();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListInvoiceResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **update6**
> CustomApiResponseInvoiceResponse update6(invoiceRequest)


### Example

```typescript
import {
    InvoiceManagementApi,
    Configuration,
    InvoiceRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new InvoiceManagementApi(configuration);

let invoiceId: string; // (default to undefined)
let invoiceRequest: InvoiceRequest; //

const { status, data } = await apiInstance.update6(
    invoiceId,
    invoiceRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **invoiceRequest** | **InvoiceRequest**|  | |
| **invoiceId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseInvoiceResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

