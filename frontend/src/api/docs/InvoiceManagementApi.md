# InvoiceManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create6**](InvoiceManagementApi.md#create6) | **POST** /api/invoices |  |
| [**delete6**](InvoiceManagementApi.md#delete6) | **DELETE** /api/invoices/{invoiceId} |  |
| [**filter7**](InvoiceManagementApi.md#filter7) | **POST** /api/invoices/filter |  |
| [**getById3**](InvoiceManagementApi.md#getbyid3) | **GET** /api/invoices/{invoiceId} |  |
| [**getOrdersForStaffToday**](InvoiceManagementApi.md#getordersforstafftoday) | **GET** /api/invoices/staff/today |  |
| [**update6**](InvoiceManagementApi.md#update6) | **PUT** /api/invoices/{invoiceId} |  |



## create6

> CustomApiResponseInvoiceResponse create6(invoiceRequest)



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { Create6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  const body = {
    // InvoiceRequest
    invoiceRequest: ...,
  } satisfies Create6Request;

  try {
    const data = await api.create6(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **invoiceRequest** | [InvoiceRequest](InvoiceRequest.md) |  | |

### Return type

[**CustomApiResponseInvoiceResponse**](CustomApiResponseInvoiceResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## delete6

> CustomApiResponseVoid delete6(invoiceId)



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { Delete6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  const body = {
    // string
    invoiceId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete6Request;

  try {
    const data = await api.delete6(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **invoiceId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## filter7

> CustomApiResponsePageInvoiceResponse filter7(searchFilter)



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { Filter7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter7Request;

  try {
    const data = await api.filter7(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageInvoiceResponse**](CustomApiResponsePageInvoiceResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getById3

> CustomApiResponseInvoiceResponse getById3(invoiceId)



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { GetById3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  const body = {
    // string
    invoiceId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById3Request;

  try {
    const data = await api.getById3(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **invoiceId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseInvoiceResponse**](CustomApiResponseInvoiceResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getOrdersForStaffToday

> CustomApiResponseListInvoiceResponse getOrdersForStaffToday()



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { GetOrdersForStaffTodayRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  try {
    const data = await api.getOrdersForStaffToday();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListInvoiceResponse**](CustomApiResponseListInvoiceResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## update6

> CustomApiResponseInvoiceResponse update6(invoiceId, invoiceRequest)



### Example

```ts
import {
  Configuration,
  InvoiceManagementApi,
} from '';
import type { Update6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new InvoiceManagementApi(config);

  const body = {
    // string
    invoiceId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // InvoiceRequest
    invoiceRequest: ...,
  } satisfies Update6Request;

  try {
    const data = await api.update6(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **invoiceId** | `string` |  | [Defaults to `undefined`] |
| **invoiceRequest** | [InvoiceRequest](InvoiceRequest.md) |  | |

### Return type

[**CustomApiResponseInvoiceResponse**](CustomApiResponseInvoiceResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

