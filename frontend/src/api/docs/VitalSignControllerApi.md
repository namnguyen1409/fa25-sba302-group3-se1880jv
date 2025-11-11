# VitalSignControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createVital**](VitalSignControllerApi.md#createvital) | **POST** /api/examinations/{id}/vitals |  |
| [**filterVitals**](VitalSignControllerApi.md#filtervitals) | **POST** /api/examinations/{id}/vitals/filter |  |
| [**getVitals**](VitalSignControllerApi.md#getvitals) | **GET** /api/examinations/{id}/vitals |  |
| [**saveOrUpdateVitals**](VitalSignControllerApi.md#saveorupdatevitals) | **PUT** /api/examinations/{id}/vitals/{vitalId} |  |



## createVital

> CustomApiResponseVitalSignResponse createVital(id, vitalSignRequest)



### Example

```ts
import {
  Configuration,
  VitalSignControllerApi,
} from '';
import type { CreateVitalRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new VitalSignControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // VitalSignRequest
    vitalSignRequest: ...,
  } satisfies CreateVitalRequest;

  try {
    const data = await api.createVital(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **vitalSignRequest** | [VitalSignRequest](VitalSignRequest.md) |  | |

### Return type

[**CustomApiResponseVitalSignResponse**](CustomApiResponseVitalSignResponse.md)

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


## filterVitals

> CustomApiResponsePageVitalSignResponse filterVitals(id, searchFilter)



### Example

```ts
import {
  Configuration,
  VitalSignControllerApi,
} from '';
import type { FilterVitalsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new VitalSignControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterVitalsRequest;

  try {
    const data = await api.filterVitals(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageVitalSignResponse**](CustomApiResponsePageVitalSignResponse.md)

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


## getVitals

> CustomApiResponseVitalSignResponse getVitals(id)



### Example

```ts
import {
  Configuration,
  VitalSignControllerApi,
} from '';
import type { GetVitalsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new VitalSignControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetVitalsRequest;

  try {
    const data = await api.getVitals(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVitalSignResponse**](CustomApiResponseVitalSignResponse.md)

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


## saveOrUpdateVitals

> CustomApiResponseVitalSignResponse saveOrUpdateVitals(id, vitalId, vitalSignRequest)



### Example

```ts
import {
  Configuration,
  VitalSignControllerApi,
} from '';
import type { SaveOrUpdateVitalsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new VitalSignControllerApi(config);

  const body = {
    // string
    id: id_example,
    // string
    vitalId: vitalId_example,
    // VitalSignRequest
    vitalSignRequest: ...,
  } satisfies SaveOrUpdateVitalsRequest;

  try {
    const data = await api.saveOrUpdateVitals(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **vitalId** | `string` |  | [Defaults to `undefined`] |
| **vitalSignRequest** | [VitalSignRequest](VitalSignRequest.md) |  | |

### Return type

[**CustomApiResponseVitalSignResponse**](CustomApiResponseVitalSignResponse.md)

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

